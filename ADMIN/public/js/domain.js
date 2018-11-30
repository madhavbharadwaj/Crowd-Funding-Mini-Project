function DomainClass(data) {
    
    this.domain = ko.observable(data.domain);
    this.editable = ko.observable(false);
  }
  function viewModel(){
    var self = this;
    self.dom= ko.observableArray([]);
    self.newDomain = ko.observable();
  
  self.addDom = function() {
    //console.log(username);
    $.ajax({
      type: 'POST',
     url:"https://test-api-man.herokuapp.com/domain",
  
      data: ko.toJS(new DomainClass({ domain: this.newDomain()})),
      success: function(data) {
        location.reload();
      },
      error: function (error) {
        
  }
  })
  };
  } 
  ko.applyBindings(new viewModel());
  
  
  