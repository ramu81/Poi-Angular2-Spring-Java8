(function(app){
	app.FwlogSortPipe = ng.core
	.Pipe({
		name: 'sort'
	})
	.Class({
		constructor: function() {},
		transform: function(fwlogs) {
			let byName = function(fwlog1,fwlog2){
				return fwlog1.msgId.localeCompare(fwlog2.msgId);
			}
			return fwlogs.slice().sort(byName);
		}
	});
})( window.app || ( window.app = {} ) );