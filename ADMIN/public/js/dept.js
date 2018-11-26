
    function CatClass(data) {
        this.category = ko.observable(data.category);
        this.editable = ko.observable(false);
      }   
      function viewModel(){
        var self = this;
        self.category  = ko.observableArray([]);
        self.newCat = ko.observable();
        self.loginTrainer = function() {
   
            $.ajax({

                type: "POST",
                url: "localhost:3000/category",
                data: ko.toJS(new CatClass({ category: this.newCat()})),
                
                success: function(msg) { 
                    // Message was sent
                  
                   console.log(data);

                },
                error: function(){
                    alert("dd");
                }
            });
        }

    

      }

ko.applyBindings(new viewModel());

      
