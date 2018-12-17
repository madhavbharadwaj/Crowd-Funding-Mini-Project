
function CategoryClass(data) {
    
    this.category = ko.observable(data.category);
    this.editable = ko.observable(false);
  }
  function viewModel(){
    var self = this;
    self.cat= ko.observableArray([]);
    self.newCategory = ko.observable();
  
  self.addCat = function() {
    //console.log(username);
    $.ajax({
      type: 'POST',
     url:"https://test-api-man.herokuapp.com/category",
  
      data: ko.toJS(new CategoryClass({ category: this.newCategory()})),
      success: function(data) {
        location.reload();
      },
      
    })
  };
} 
  ko.applyBindings(new viewModel());
  
  
  