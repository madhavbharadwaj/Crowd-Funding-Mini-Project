$(function (){
$.ajax({
    type: 'GET',
    url: 'https://bms-icl-yoga.herokuapp.com/trainer/',
    success: function(data) {
        $.getJSON("https://bms-icl-yoga.herokuapp.com/trainer/", function (data) {

  var all = new Array();

 // console.log(data.trainer[0]);
     //   console.log(data.trainer[0].trainer_details.f_name);
  for(i=0;i<data.count;i++)
    {
        all[i] = [
            [ data.trainer[i].trainer_details.f_name,data.trainer[i].trainer_details.m_name,data.trainer[i].trainer_details.l_name]
            
        ]
    }
  $('#example').DataTable( {
    "columnDefs": [
    {
        
        "targets": [ 0 ],
        "visible": false,
    },
],
columns: [
    { title: "ID"},
    { title: "SL_NO" },
    { title: "First Name" },
    { title: "Middle Name" },
    { title: "Last Name" },
    { title: "Phone" },
    { title: "Department" },
    
    
]
} )

for(i=0;i<data.count;i++)
{
    
   // $("#example tbody").append("<tr> <td class='nr'>"+data.trainer[i]._id+" </td></tr>");
    $('#example').DataTable().row.add( [data.trainer[i].trainer_details._id, (i+1),data.trainer[i].trainer_details.f_name,data.trainer[i].trainer_details.m_name,data.trainer[i].trainer_details.l_name,data.trainer[i].trainer_details.phone])
    .draw()
    .node();
}
});
   
}
});  
})