//ADD CATEGORY
function CategoryClass(data) {
    
  this.category = ko.observable(data.category);
  this.editable = ko.observable(false);
}
//ADD DOMAIN
function DomainClass(data) {
    
  this.domain = ko.observable(data.domain);
  this.editable = ko.observable(false);
}

function viewModel(){
  var self = this;
  self.cat= ko.observableArray([]);
  self.newCategory = ko.observable();
  self.dom= ko.observableArray([]);
  self.newDomain = ko.observable();
//POST REQUEST FOR ADDING CATEGORY(DEPARTMENT)
  self.addCat = function() {
    console.log("username");
    $.ajax({
      type: 'POST',
     url:"https://crowd-src.herokuapp.com/category",
  
      data: ko.toJS(new CategoryClass({ category: this.newCategory()})),
      success: function(data) {
        location.reload();
      }
      
    })
  };
  //POST REQUEST FOR ADDING DOMAIN
  self.addDomain = function() {
    console.log("s");
    $.ajax({
      type: 'POST',
     url:"https://crowd-src.herokuapp.com/domain",
  
      data: ko.toJS(new DomainClass({ domain: this.newDomain()})),
      success: function(data) {
        console.log("s");
        location.reload();
      }
      
    })
  };
} 
  ko.applyBindings(new viewModel());
  
  
  