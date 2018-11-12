
    function TrainerClass(data) {
        this.email = ko.observable(data.email);
        this.password = ko.observable(data.password);
        this.editable = ko.observable(false);
      } 
     
        
        
      function viewModel(){
        var self = this;
        self.trainer  = ko.observableArray([]);
        self.newTrainerLEmail = ko.observable();
        self.newTrainerLPass = ko.observable();

        self.loginTrainer = function() {
           
             var sLoader = $('.submit-loader'); 
               
            $.ajax({

                type: "POST",
                url: "https://bms-icl-yoga.herokuapp.com/trainer/login",
                data: ko.toJS(new TrainerClass({ email: this.newTrainerLEmail, password: this.newTrainerLPass()})),
                beforeSend: function() { 
    
                    sLoader.slideDown("slow");

                },
                success: function(msg) { 
                    // Message was sent
                  
                    sLoader.slideUp("slow");
                        $('.message-warning').fadeOut();
                        $('#contactForm').fadeOut();
                        $('.message-success').fadeIn();
                        
                        window.location.pathname = "dashboard.html";
                    

                },
                error: function() {

                    sLoader.slideUp("slow");
                    $('.message-warning').html("Something went wrong. Please try again.");
                    $('.message-warning').slideDown("slow");

                }

            });
        }

    

      }

ko.applyBindings(new viewModel());

      
