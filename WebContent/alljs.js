

function selectUpdate(sel){
	$.ajaxSetup({async: false,cache: false});
	$.post("./CoursePickerController", {requirementId:sel.options[sel.selectedIndex].value});
   	$.get("./courseAccordion.jsp", function(data){
   		$("#courseAccordion").html(data);
  	});
};

function registerClass(sel){
	$.ajaxSetup({async: false,cache: false});
	$.post("./CoursePickerController", {callNum:sel.name});
	$.get("./schedule.jsp", function(data){
		$("#canvasSchedule").html(data);
	});
};
  	
function drawCanvas(){
	var c=document.getElementById("schedule");
	var ctx=c.getContext("2d");
	drawDaysTimes();
	drawGrid();
};

//Draw the Days and Times
function drawDaysTimes(){
	var c=document.getElementById("schedule");
	var ctx=c.getContext("2d");
	ctx.font="bold 18px Arial";
	ctx.fillText("Monday",105,30);
	ctx.fillText("Tuesday",205,30);
	ctx.fillText("Wed.",305,30);
	ctx.fillText("Thursday",405,30);
	ctx.fillText("Friday",505,30);
	ctx.fillText("8:00am",5,70);
	ctx.fillText("9:00",5,110);
	ctx.fillText("10:00",5,150);
	ctx.fillText("11:00",5,190);
	ctx.fillText("12:00pm",5,230);
	ctx.fillText("1:00",5,270);
	ctx.fillText("2:00",5,310);
	ctx.fillText("3:00",5,350);
	ctx.fillText("4:00",5,390);
	ctx.fillText("5:00",5,430);
	ctx.fillText("6:00",5,470);
	ctx.fillText("7:00",5,510);
	ctx.fillText("8:00",5,550);
	ctx.fillText("9:00",5,590);
};

//Draw the gridlines
function drawGrid(){
	var c=document.getElementById("schedule");
	var ctx=c.getContext("2d");


	//Vertical
	for(var i=1; i<6; i++){
		ctx.moveTo(i*100,0);
		ctx.lineTo(i*100,600);
		ctx.stroke();
	}
	
	//Horizontal
	ctx.strokeStyle = "#A8A8A8";
	for(var i=1; i<15; i++){
		ctx.moveTo(0,40*i);
		ctx.lineTo(600,40*i);
		ctx.stroke();
	}    
};

function drawClass(prefix, num, start, end, days){
	var c=document.getElementById("schedule");
	var ctx=c.getContext("2d");
	var newStart = "";
	var newEnd = "";
	if(start.length == 4){
		newStart = "0" + newStart;
	}
	if(end.length == 4){
		newEnd = "0" + newEnd;
	}
	ctx.font = "14px Times New Roman";
	
		 
	var xStartHr = parseInt((start.substring(0,2)));
	var xEndHr = parseInt((end.substring(0,2)));	

	if(xStartHr >= 8){
		xStartHr = xStartHr%8;
	}
	else if(xStartHr < 8){
		xStartHr += 4;
	}
	
	if(xEndHr >= 8){
		xEndHr = xEndHr%8;
	}
	else if(xEndHr < 8){
		xEndHr += 4;
	}
	
	var xStartMin = parseInt(start.substring(2,4));
	var xEndMin = parseInt(end.substring(2,4));

	var xStart = (40 + 40*(xStartHr) + .6666667*xStartMin);
	var xEnd = (40 + 40*xEndHr + .6666667*xEndMin);
	
	for(var i=0; i<days.length; i++){
		if(days[i] === 'M'){
			ctx.fillStyle="black";
			ctx.fillRect(100,xStart,100,xEnd-xStart);
			ctx.fillStyle="white";
			ctx.fillText(prefix,115,xStart+25);
		}
		else if(days[i] === 'T'){
			ctx.fillStyle="black";
			ctx.fillRect(200,xStart,100,xEnd-xStart);
			ctx.fillStyle="white";
			ctx.fillText(prefix,215,xStart+25);

		}
		else if(days[i] === 'W'){
			ctx.fillStyle="black";
			ctx.fillRect(300,xStart,100,xEnd-xStart);
			ctx.fillStyle="white";
			ctx.fillText(prefix,315,xStart+25);

		}
		else if(days[i] === 'R'){
			ctx.fillStyle="black";
			ctx.fillRect(400,xStart,100,xEnd-xStart);
			ctx.fillStyle="white";
			ctx.fillText(prefix,415,xStart+25);

		}
		else if(days[i] === 'F'){
			ctx.fillStyle="black";
			ctx.fillRect(500,xStart,100,xEnd-xStart);
			ctx.fillStyle="white";
			ctx.fillText(prefix,515,xStart+25);
		}
		
	}
};	

function clearCanvas(){
	var c=document.getElementById("schedule");
	var ctx=c.getContext("2d");
	ctx.clearRect(0,0,600,600);
};


