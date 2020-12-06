<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title> Covid-19 </title>
    <style><%@include file="../css/style.css"%></style>
</head>
<a href="<c:url value="/login" />">Logout</a>

<body>
    <hr>
    <div>
    <h1>Covid-19</h1>
        <form method="get" action="/get/">
            <select name="id1">
                                <option value=" ">Click to Chose a Country</option>
                                <option value="USA">United States</option>
                                <option value="Argentina">Argentina</option>
                                <option value="Austria">Austria</option>
                                <option value="Bangladesh">Bangladesh</option>
                                <option value="Belgium">Belgium</option>
                                <option value="Brazil">Brazil</option>
                                <option value="Canada">Canada</option>
                                <option value="Chile">Chile</option>
                                <option value="Colombia">Colombia</option>
                                <option value="Czechia">Czechia</option>
                                <option value="Dominican Republic">Dominican Republic</option>
                                <option value="Egypt">Egypt</option>
                                <option value="France">France</option>
                                <option value="Germany">Germany</option>
                                <option value="Honduras">Honduras</option>
                                <option value="India">India</option>
                                <option value="Indonesia">Indonesia</option>
                                <option value="Iran">Iran</option>
                                <option value="Iraq">Iraq</option>
                                <option value="Israel">Israel</option>
                                <option value="Italy">Italy</option>
                                <option value="Japan">Japan</option>
                                <option value="Kuwait">Kuwait</option>
                                <option value="Lebanon">Lebanon</option>
                                <option value="Mexico">Mexico</option>
                                <option value="Nepal">Nepal</option>
                                <option value="Netherlands">Netherlands</option>
                                <option value="Oman">Oman</option>
                                <option value="Pakistan">Pakistan</option>
                                <option value="Peru">Peru</option>
                                <option value="Portugal">Portugal</option>
                                <option value="Philippines">Philippines</option>
                                <option value="Poland">Poland</option>
                                <option value="Qatar">Qatar</option>
                                <option value="Russia">Russia</option>
                                <option value="Saudi Arabia">Chile</option>
                                <option value="South Africa">South Africa</option>
                                <option value="Spain">Spain</option>
                                <option value="Switzerland">Switzerland</option>
                                <option value="Turkey">Turkey</option>
                                <option value="UAE">UAE</option>
                                <option value="UK">United Kingdom</option>
                                <option value="Ukraine">Ukraine</option>
                                <option value="Vietnam">Vietnam</option>
                                <option value="Western Sahara">Western Sahara</option>
                                <option value="Yemen">Yemen</option>
                                <option value="Zimbabwe">Zimbabwe</option>
            </select>

            <input type="submit" value="Submit">


        </form>
        </div>
    </hr>



    <h2>Covid Cases Information</h2>
        <table>
                <tr >
                    <th>Country</th>
                    <th>Total Cases</th>
                    <th>Active Cases</th>
                    <th>Recovered Cases</th>
                    <th>Total Deaths</th>
                    <th>Dates</th>
                </tr>
                <tr>
                    <td>  <%=request.getParameter("Country_text")%> </td>
                    <td>  <%=request.getParameter("Total Cases_text")%> </td>
                    <td> <%=request.getParameter("Active Cases_text")%> </td>
                    <td> <%=request.getParameter("Total Recovered_text")%> </td>
                    <td> <%=request.getParameter("Total Deaths_text")%> </td>
                    <td> <%=request.getParameter("Last Update")%> </td>
                </tr>
        </table>



    <form method="post" action="/post/" >

    <div>
            <input type="submit" value="Click to Save for Snapshot">

    </div>
    <h2>SnapShot Information</h2>

    <table  class="w3-table-all">
        <tr class="w3-blue">


            <th>Country</th>
            <th>Total Cases</th>
            <th>Active Cases</th>
            <th>Recovered Cases</th>
            <th>Total Deaths</th>
            <th>Dates</th>
            <th>Delete from SnapShot History</th>
        </tr>



        <c:forEach  var = "covid" items = "${covidlist}">
            <tr>

                <td>${covid.getCountry()}</td>
                <td>${covid.getTcase()}</td>
                <td>${covid.getAcase()}</td>
                <td>${covid.getRcase()}</td>
                <td>${covid.getDeath()}</td>
                <td>${covid.getDate()}</td>
                <td> <a href="/delete/${covid.getId()}">Delete</a></td>

            </tr>
        </c:forEach>




            <div>
                <h2></h2><h3 > <input type="hidden"name="id7" value=<%=request.getParameter("Country_text")%>> </h3>
                <h2></h2><h3 > <input type="hidden"name="id2" value=<%=request.getParameter("Total Cases_text")%>> </h3>
                <h2></h2><h3 > <input type="hidden"name="id3" value=<%=request.getParameter("Active Cases_text")%>> </h3>
                <h2></h2><h3 > <input type="hidden"name="id4" value=<%=request.getParameter("Total Recovered_text")%>> </h3>
                <h2></h2><h3 > <input type="hidden"name="id5" value=<%=request.getParameter("Total Deaths_text")%>> </h3>
                <h2></h2><h3 > <input type="hidden"name="id6" value=<%=request.getParameter("Last Update")%>> </h3>
            </div>




         </form>

    </table>


             <form method="post" action="/edit/">
                           <div><h2>Country</h2><h3 > <input type="text"name="Country" </h3></div>
                           <div><h2>Total Cases</h2><h3 > <input type="text"name="total" </h3></div>
                           <div><h2>Active Case</h2><h3 > <input type="text"name="active" </h3></div>
                           <div><h2>Recovered Cases</h2><h3 > <input type="text"name="reco" </h3></div>
                           <div><h2>Total Death</h2><h3 > <input type="text"name="death" </h3></div>
                           <div><h2>Current Date</h2><h3 > <input type="text"name="date" </h3></div>
                           <div><input type="submit" value="Click to Add the Desired Information "><div>
             </form>


             <div>
             <script var = "covid" items = "${covidlist}" type="text/javascript">
             window.onload = function () {

             var chart = new CanvasJS.Chart("chartContainer", {
             	theme: "dark2", // "light2", "dark1", "dark2"
             	animationEnabled: true, // change to true
             	title:{
             		text: "Chart of total Active Cases"
             	},
             	data: [
             	{
             		// Change type to "bar", "area", "spline", "pie",etc.
             		type: "bar",
             		dataPoints: [
             			{ label: "Austria",  y: 297245  },
             			{ label: "USA", y: 14772535  },
             			{ label: "Pakistan", y:  413191 },
             			{ label: "Russia",  y: 2402949 },
             			{ label: "Japan",  y: 155232  }
             		]
             	}
             	]
             });
             chart.render();

             }
             </script>
             </head>
             <body>
             <div id="chartContainer" style="height: 370px; width: 100%;"></div>
             <script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>
             </div>








</body>
</html>