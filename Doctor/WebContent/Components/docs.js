$(document).ready(function() 
{  
		$("#alertSuccess").hide();
	    $("#alertError").hide(); 
});

// SAVE ----------------------------------------------------
$(document).on("click", "#btnSave", function(event)
{ 
	 
	// Clear status msges--------------------- 
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide();
	
	// Form validation------------------- 
	var status = validateDocForm(); 
	if (status != true) 
	{  
		$("#alertError").text(status);  
		$("#alertError").show();  
		return; 
	} 
	
	// If Valid ------------------
	var type = ($("#hidDocIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax({
		
		url : "DocsAPI",
		type : type,
		data: $("#formDoc").serialize(),
		dataType: "text",
		complete : function(response, status)
		{
			onDocSaveComplete(response.responseText, status);
		}
	});
}); 

function onDocsaveComplete(response, status)
{
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
					$("#alertSuccess").text("Successfully saved.");
					$("#alertSuccess").show();
					
					$("#divDocsGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
						$("#alertError").text(resultSet.data);
						$("#alertError").show();
					}
		}else if (status == "error")
			{
				$("#alertError").text("Error while saving");
				$("#alertError").show();
			}else
				{
					$("#alertError").text("Unknown error while saving..");
					$("#alertError").show();
				}
			
			    $("#hidDocIDSave").val("");
			    $("#formDoc")[0].reset();
	
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
			$("#hidDocIDSave").val($(this).closest("tr").find('#hidDocIDUpdate').val());     
			$("#docCode").val($(this).closest("tr").find('td:eq(0)').text());     
			$("#docName").val($(this).closest("tr").find('td:eq(1)').text());    
			$("#docFee").val($(this).closest("tr").find('td:eq(2)').text());     
			$("#docDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
		});

// Remove=========================================
$(document).on("click","btnRemove", function(event)
	{	
	$.ajax(
			{
		
		url : "DocsAPI",
		type : "DELETE",
		data: "docID" + $(this).data("docid"),
		dataType: "text",
		complete : function(response, status)
		{
			onDocDeleteComplete(response.responseText, status);
		}
	});
});		

function onDocDeleteComplete(response, status)
{
	if (status == "success")
		{
			var resultSet = JSON.parse(response);
			
			if (resultSet.status.trim() == "success")
				{
					$("#alertSuccess").text("Successfully delete");
					$("#alertSuccess").show();
					
					$("#divDocGrid").html(resultSet.data);
				}else if (resultSet.status.trim() == "error")
					{
						$("#alertError").text(resultSet.data);
						$("#alertError").show();
					}
		}else if (status == "error")
		{
			$("#alertError").text("Error while deleting");
			$("#alertError").show();
		}else
			{
				$("#alertError").text("Unknown error while deleting..");
				$("#alertError").show();
			}
		
}


//CLIENT-MODEL================================================================ 
function validateDocForm() 
{  
	// CODE  
	if ($("#docCode").val().trim() == "") 
	{  
		return "Insert Doc Code.";  
	} 

	// NAME  
	if ($("#docName").val().trim() == "")  
	{  
		return "Insert Doc Name.";
	} 

	// Fee-------------------------------  
	if ($("#docFee").val().trim() == "") 
	{   
		return "Insert Doc Fee."; 
	} 

	// is numerical value  
	var tmpFee = $("#docPrice").val().trim();  
	if (!$.isNumeric(tmpFee))  
	{   
		return "Insert a numerical value for Doc Fee.";  
	} 

	// convert to decimal price  
	$("#docFee").val(parseFloat(tmpDoc).toFixed(2)); 

	// DESCRIPTION------------------------  
	if ($("#docDesc").val().trim() == "")  
	{   
		return "Insert Doc Description."; 
	} 

return true; } 
