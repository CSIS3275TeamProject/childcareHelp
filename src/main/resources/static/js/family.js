let index = 1;
function addChildInfo(){
    let childInfo = document.getElementById("childrenInfo");
    let childInfoItems = "<label htmlFor=''>Child's Name</label>"
        +"<input type='text' name='children["+index+"].name' placeholder='name of your child' value='child name'>"
        +"<label htmlFor=''>Child's Gender</label>"
        +"<select name='children["+index+"].gender' id='gender'>"
        +"<option value='boy'>boy</option>"
        +"<option value='girl'>girl</option>"
        +"</select>"
        +"<label htmlFor=''>Date of Birth</label>"
        +"<input type='date' value='2021-04-09' name='children["+index+"].dateOfBirth'>";
    childInfo.innerHTML += childInfoItems;
    index++;
}