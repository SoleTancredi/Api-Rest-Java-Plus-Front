// Call the dataTables jQuery plugin
$(document).ready(function() {
    uploadUsers();
  $('#users').DataTable();
});

async function uploadUsers(){

      const request = await fetch('api/users', {
        method: 'GET',
        headers: getHeaders()
      });
      const users = await request.json();

      let listHtml = '';

      for(let user of users){
             let textTelephone = user.telephone == null ? '-' : user.telephone;
             let buttonDelete = '<a href="#" onclick="deleteUser(' + user.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
             let userHtml = '<tr><td>'+user.id+'</td><td>'+user.name+' '+user.lastName+'</td><td>'+user.email+'</td><td>'+textTelephone+'</td><td>'+buttonDelete+'</td></tr>';
             listHtml += userHtml;
      }

      console.log(users);

      document.querySelector('#users tbody').outerHTML = listHtml;
}

function getHeaders(){
    return  {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization': localStorage.token
            }
}

async function deleteUser(id){

    if(!confirm('Do you want to delete the user?'))
        return;

    const request = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: getHeaders()
      });
    location.reload();
}
