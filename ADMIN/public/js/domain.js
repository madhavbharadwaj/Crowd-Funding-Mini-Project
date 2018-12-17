function DomainClass(data) {
    
    this.domain = ko.observable(data.domain);
    this.editable = ko.observable(false);
  }
  function viewModel(){
    var self = this;
    self.dom= ko.observableArray([]);
    self.newDomain = ko.observable();
  
  self.addDomain = function() {
    console.log("s");
    $.ajax({
      type: 'POST',
     url:"https://test-api-man.herokuapp.com/domain",
  
      data: ko.toJS(new DomainClass({ domain: this.newDomain()})),
      success: function(data) {
        console.log("s");
        //location.reload();
      }
      
    })
  };
} 
ko.applyBindings(new viewModel());
  
  
  