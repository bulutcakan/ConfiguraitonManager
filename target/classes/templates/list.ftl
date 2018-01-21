<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Add New Config Parameters </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.param.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.param.name" id="uname" class="paramname form-control input-sm" placeholder="Enter config param name" required />
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="type">Type</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.param.type" id="type" class="form-control input-sm" placeholder="Enter config param type" required/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="value">Value</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.param.value" id="value" class="form-control input-sm" placeholder="Enter config param value" required/>
	                        </div>
	                    </div>
	                </div>
	                
	                 <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="isActive">Is Active</label>
	                        <div class="col-md-7">
							<select ng-model="ctrl.param.isActive" ng-init="somethingHere = isActive[0]">
							<option ng-repeat="x in isActive" ng-value="{{x.value}}">{{x.text}}</option>
							</select>
	                        </div>
	                    </div>
	                </div>

					<div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="applicationName">Application Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.param.applicationName" id="applicationName" class="form-control input-sm" placeholder="Enter application name." required />
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.param.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Config Parameters </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>TYPE</th>
		                <th>VALUE</th>
		                <th>ISACTIVE</th>
		                <th>APPLICATION NAME</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllParams()">
		                <td>{{u.id}}</td>
		                <td>{{u.name}}</td>
		                <td>{{u.type}}</td>
		                <td>{{u.value}}</td>
		                 <td>{{u.isActive}}</td>
		                <td>{{u.applicationName}}</td>
		                <td><button type="button" ng-click="ctrl.editParam(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeParam(u.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>