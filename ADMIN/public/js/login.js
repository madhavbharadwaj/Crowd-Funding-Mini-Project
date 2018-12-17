
function TrainerClass(data) {
    this.email = ko.observable(data.email);
    this.password = ko.observable(data.password);
    this.editable = ko.observable(false);
}  
//BINDING USING KNOCKOUTJS  
function viewModel(){
    var self = this;
    self.trainer  = ko.observableArray([]);
    self.newTrainerLEmail = ko.observable();
    self.newTrainerLPass = ko.observable();

    self.loginTrainer = function() {
        
        var sLoader = $('.submit-loader'); 
            
        $.ajax({

            type: "POST",
            url: "https://crowd-src.herokuapp.com/admin/login",
            data: ko.toJS(new TrainerClass({ email: this.newTrainerLEmail, password: this.newTrainerLPass()})),
            success: function(msg) { 
                //  LOGIN SUCCESSFUL
                
                //sLoader.slideUp("slow");
                //$('.message-warning').fadeOut();
                //$('#contactForm').fadeOut();
                //$('.message-success').fadeIn();
                
                window.location.pathname = "home.html";   
            },
            error: function() {
                //DISPLAY ERROR MESSAGE
                sLoader.slideUp("slow");
                $('.message-warning').html("Invalid email or password!");
                $('.message-warning').slideDown("slow");

            }

        });
    }
}

ko.applyBindings(new viewModel());

      
