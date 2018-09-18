(function(app){
	app.FwlogService = ng.core
	.Class({
		constructor: [ng.http.Http, function(http) {
			this.http = http;
		}],
		get: function() {
			return this.http.get('fwlogs')
			.map(this.extractFwlog)
			.catch(this.extractError);
		},
		extractFwlog: function(response) {
			if(response.status === 200)
				return response.json();
			else 
				throw new Error('Error getting fwlogs');
		},
		extractError: function(error) {
			return Rx.Observable.throw(error.message || 'error getting fwlogs');
		}
	});
})( window.app || ( window.app = {} ) );