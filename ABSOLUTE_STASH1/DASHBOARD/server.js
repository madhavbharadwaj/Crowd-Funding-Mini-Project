var express=require('express');
var app=express();


var port = process.env.PORT || 5000;
/*--------------------Routing Over----------------------------*/
app.use(express.static('public'));
app.listen(port,function(){
console.log("Express Started on Port :" +port);
}); 