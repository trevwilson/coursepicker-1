
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>Course Finder</title>
</head>
<body>
	<div style="float:left">
	<h2>Select Requirement:</h2>
	<form>
		<select name="reqID">
			<option value="1">Cultural Diversity Requirement</option>
			<option value="2">Environmental Literacy Requirement</option>
			<option value="3">Core Curriculum I: Foundation Courses</option>
			<option value="4">Core Curriculum II: Physical Sciences</option>
			<option value="5">Core Curriculum II: Life Sciences</option>
			<option value="6">Core Curriculum III: Quantitative Reasoning</option>
			<option value="7">Core Curriculum IV: World Languages and Culture</option>
			<option value="8">Core Curriculum IV: Humanities and Arts</option>
			<option value="9">Core Curriculum V: Social Sciences</option>
			<option value="10">Franklin College Foreign Language</option>
			<option value="11">Franklin College Literature</option>
			<option value="12">Franklin College Fine Arts/Philosophy/Religion</option>
			<option value="13">Franlin College History</option>
			<option value="14">Franklin CollegeSocial Sciences other than History</option>
			<option value="15">Franklin College Biological Sciences</option>
			<option value="16">Franklin College:Physical Sciences</option>
			<option value="17">Franklin College Multicultural Requirement</option>
			<option value="18">Core CurriculumVI: Major related courses</option>
			<option value="19">Computer Science Major Courses</option>
		</select>
		<input type="submit" value="Search"/>
	</form>
	<br>
	<h2>Classes to fulfill the requirement:</h2>
	<table border="1">
		<form>
			<tr><th>Department</th><th>Number</th><th>Time</th><th>Days</th><th>Add to Schedule</th></tr>
			<tr><td>CSCI</td><td>4300</td><td>10:10-11:00</td><td>MWF</td><td><input type="submit" value="Add"></td>
			<tr><td>CSCI</td><td>4320</td><td>11:15-12:05</td><td>MWF</td><td><input type="submit" value="Add"></td>
			<tr><td>CSCI</td><td>4500</td><td>2:00-3:15</td><td>TR</td><td><input type="submit" value="Add"></td>
		</form>
	</table>
	</div>
	<div style="float:right">
    <h2>Schedule View</h2>
    <canvas id="schedule" width="600" height="600"
    style="border:1px solid #000000;">
    </canvas>
	<h2>Current Classes</h2>
	<table border="1">
		<form>
			<tr><th>Department</th><th>Number</th><th>Time</th><th>Days</th><th>Remove from Schedule</th></tr>
			<tr><td>CSCI</td><td>4300</td><td>10:10-11:00</td><td>MWF</td><td><input type="submit" value="Remove"></td>
			<tr><td>CSCI</td><td>4320</td><td>11:15-12:05</td><td>MWF</td><td><input type="submit" value="Remove"></td>
			<tr><td>CSCI</td><td>4500</td><td>2:00-3:15</td><td>TR</td><td><input type="submit" value="Remove"></td>
		</form>
	</table>
	
	</div>
    <script>
        var c=document.getElementById("schedule");
        var ctx=c.getContext("2d");
        
		//Draw the Days and Times
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
        
        //Draw the gridlines
        ctx.moveTo(100,0);
        ctx.lineTo(100,600);
        ctx.stroke();
        ctx.moveTo(200,0);
        ctx.lineTo(200,600);
        ctx.stroke();
        ctx.moveTo(300,0);
        ctx.lineTo(300,600);
        ctx.stroke();        
        ctx.moveTo(400,0);
        ctx.lineTo(400,600);
        ctx.stroke();
        ctx.moveTo(500,0);
        ctx.lineTo(500,600);
        ctx.stroke();
        
        ctx.strokeStyle = "#A8A8A8";
        for(var i=1; i<15; i++){
            ctx.moveTo(0,40*i);
            ctx.lineTo(600,40*i);
            ctx.stroke();
        }        
        
        ctx.font = "14px Times New Roman"
        ctx.fillStyle="black";
        ctx.fillRect(100,126,100,33);
        ctx.fillRect(300,126,100,33);
        ctx.fillRect(500,126,100,33);
        
        ctx.fillRect(100,160,100,33);
        ctx.fillRect(300,160,100,33);
        ctx.fillRect(500,160,100,33);
        
        ctx.fillRect(200,280,100,50);
        ctx.fillRect(400,280,100,50);
        
        ctx.fillStyle="white";
        ctx.fillText("CSCI4300",115,150);
        ctx.fillText("CSCI4300",315,150);
        ctx.fillText("CSCI4300",515,150);

        ctx.fillText("CSCI4320",115,184);
        ctx.fillText("CSCI4320",315,184);
        ctx.fillText("CSCI4320",515,184);

        ctx.fillText("CSCI4500",215,310);
        ctx.fillText("CSCI4500",415,310);



    </script>
</body>
</html>