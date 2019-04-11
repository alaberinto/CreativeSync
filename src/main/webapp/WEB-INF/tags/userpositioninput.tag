<%-- 
    Document   : userpositioninput
    Created on : Apr 4, 2019, 6:53:39 PM
    Author     : 759388
--%>
<form method="post" action="Reports">
    <select class="selectpicker form-control" name="positions" id="users" multiple data-live-search="true" title="Select Users" data-header="Select Users" required>
        <option value="4">Freelancers</option>
        <option value="3">Design Leads</option>
        <option value="2">Coorinators</option>
        <option value="1">Admins</option>
    </select>
    <button type="submit" name="reportInput" value="positions" class="btn btn-block button-red-solid mb-2">Generate</button>
</form>