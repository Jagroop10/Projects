const todoList = document.getElementById("todo-list");
const newTask = document.getElementById("new-task");

function addTask() {
  if (newTask.value === "") {
    alert("Please enter a task");
  } else {
    const task = document.createElement("li");
    task.classList.add("task");
    task.innerHTML = `
      <input type="checkbox">
      <p>${newTask.value}</p>
      <button class="delete-task">X</button>
    `;
    todoList.appendChild(task);
    newTask.value = "";
  }
}

newTask.addEventListener("keypress", function(event) {
  if (event.key === "Enter") {
    addTask();
  }
});

todoList.addEventListener("click", function(event) {
  if (event.target.tagName === "INPUT") {
    const task = event.target.parentElement;
    task.classList.toggle("completed");
  } else if (event.target.classList.contains("delete-task")) {
    const task = event.target.parentElement;
    todoList.removeChild(task);
  }
});
