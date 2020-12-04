<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title> Covid-19 </title>
    <style><%@include file="../css/style.css"%></style>
</head>
<body>
    <hr>
    <h1>Covid-19</h1>
        <form method="get" action="/get/">
            <select name="id1">
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
    </hr>



    <h2>Events Information</h2>

    <form method="post" action="/post/" >
    <table  class="w3-table-all">
        <tr class="w3-blue">


            <th>Total Cases</th>
            <th>Active Cases</th>
            <th>Recovered Cases</th>
            <th>Total Deaths</th>
            <th>Dates</th>
        </tr>


        <c:forEach  var = "covid" items = "${covidlist}">
            <tr>

                <td>${covid.getTcase()}</td>
                <td>${covid.getAcase()}</td>
                <td>${covid.getRcase()}</td>
                <td>${covid.getDeath()}</td>
                <td>${covid.getDate()} <a href="/delete/${covid.getId()}">Delete</a></td>

            </tr>

        </c:forEach>
    <div>
        <h2>Total</h2><h3 >  <%=request.getParameter("Total Cases_text")%> </h3>
        <h2></h2><h3 > <input type="hidden"name="id2" value=<%=request.getParameter("Total Cases_text")%>> </h3>

        <h2>Active</h2><h3 >  <%=request.getParameter("Active Cases_text")%> </h3>
        <h2></h2><h3 > <input type="hidden"name="id3" value=<%=request.getParameter("Active Cases_text")%>> </h3>

        <h2>Recovered</h2><h3 >  <%=request.getParameter("Total Recovered_text")%> </h3>
        <h2></h2><h3 > <input type="hidden"name="id4" value=<%=request.getParameter("Total Recovered_text")%>> </h3>

        <h2>Death</h2><h3 >  <%=request.getParameter("Total Deaths_text")%> </h3>
        <h2></h2><h3 > <input type="hidden"name="id5" value=<%=request.getParameter("Total Deaths_text")%>> </h3>

        <h2>Date</h2><h3 > <%=request.getParameter("Last Update")%> </h3>
        <h2></h2><h3 > <input type="hidden"name="id6" value=<%=request.getParameter("Last Update")%>> </h3>

    </div>

        <input type="submit" value="Submit for Snapshot">


         </form>




    </table>
<a href="/chart" >Open</a>


</body>
</html>