
function AdminClass(data) {
    this.username = ko.observable(data.username);
    this.mobile = ko.observable(data.mobile);
    this.email = ko.observable(data.email);
    
    this.editable = ko.observable(false);
}

//REGISTER AN ADMIN
//DATA-BINDIND VALUES FOR FORM ELEMENTS IN Sign_up.html
function viewModel(){
    var self = this;
    self.admin  = ko.observableArray([]);
    self.newAdminUName = ko.observable();
    self.newAdminMobile = ko.observable();
    self.newAdminEmail = ko.observable();
    //addAdmin called in FORM in Sign-up.html
    self.addAdmin = function() 
    {
        //console.log("x");
        //console.log(username);
        $.ajax({
            type: 'POST',
            
            url: 'https://crowd-src.herokuapp.com/admin/signup',

            data: ko.toJS(new AdminClass({username: this.newAdminUName(),email:  this.newAdminEmail(),mobile:  this.newAdminMobile()})),
            success: function(data) {
                console.log("x"); alert("hi");
                console.log("admin added!", data); 
                alert("CHECK YOUR REGISTERED MAIL ID TO CHANGE PASSWORD");//the new item is returned with an ID
                window.history.go(0);
            } 
        })
    };
} 
ko.applyBindings(new viewModel());

    
    