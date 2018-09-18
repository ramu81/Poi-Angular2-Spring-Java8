(function(app){
	app.FwlogComponent = ng.core
	.Component({
		selector: 'fwlog',
		templateUrl: 'app/fwlog.component.html',
		providers: [app.FwlogService]
	})
	.Class({
		constructor: [app.FwlogService, function(service){
			this.service = service;
			this.message = ' ';
			this.fwlogs = [];
		}],
		getFwlogs: function(){			
			this.service.get()
			.subscribe(this.updateFwlogs.bind(this),
					this.updateMessage.bind(this));
		},
		updateFwlogs: function(fwlogs){
			this.fwlogs = fwlogs;
		},
		updateMessage: function(error){
			this.message = error;
		},
		ngOnInit: function(){
			this.getFwlogs();
		}
	});
})( window.app || ( window.app = {} ) );