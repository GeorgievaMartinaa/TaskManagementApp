
function updateTaskDone(checkbox){
    var taskId = checkbox.getAttribute("data-id");
    var done = checkbox.checked;

    console.log(taskId)
    console.log(done)

    axios.patch(`/tasks/${taskId}/done`, {
        done:done
    })
        .then(response => {
            console.log("Task updated ", response.data);
        })
        .catch(error =>{
            console.log("Error ",error);
        });
}