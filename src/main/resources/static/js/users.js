// Call the dataTables jQuery plugin
$(document).ready(function() {
    uploadUsers();
  $('#users').DataTable();
});

async function uploadUsers(){

      const request = await fetch('users', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      });
      const users = await request.json();

      let listHtml = '';

      for(let user of users){
             let userHtml = '<tr><td>'+user.id+'</td><td>'+user.name+' '+user.lastName+'</td><td>'+user.email+'</td><td>'+user.telephone+'<td><i class="fas fa-trash"></i></td>';
             listHtml += userHtml;
      }

      console.log(users);

      document.querySelector('#users tbody').outerHTML = listHtml;
}
