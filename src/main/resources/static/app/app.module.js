(function(app){
	app.AppModule = 
	ng.core.NgModule({
		imports: [
		ng.platformBrowser.BrowserModule,
		ng.http.HttpModule,
		ng.forms.FormsModule
		],
		declarations: [
		app.FwlogComponent,
		app.FwlogSortPipe
		],
		providers: [],
		bootstrap: [ app.FwlogComponent ]
	})
	.Class({
		constructor: function(){}
	});
})( window.app || ( window.app = {} ) );