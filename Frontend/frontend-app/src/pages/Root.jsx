import AddTaskButton from "../components/UI/Button/AddTaskButton";
import Footer from "../components/UI/Footer/Footer";
import Navbar from "../components/UI/Header/Navbar";
import TaskPanel from "../components/UI/Panel/TaskPanel";
import { fetchTasks } from "../services/TaskService";
import { useQuery } from "react-query";
import { getUserName } from "../services/UserService";
import { useNavigate } from "react-router-dom";

function Root() {
  const navigate = useNavigate()

  const categorizeTasks = () => {
    const todoTasks = data.filter((task) => task.status === 1);
    const inProgressTasks = data.filter((task) => task.status === 2);
    const doneTasks = data.filter((task) => task.status === 3);

    return { todoTasks, inProgressTasks, doneTasks };
  };

  const { data,error, isLoading } = useQuery("tasks", () =>
    fetchTasks(getUserName())
  );

  if (isLoading) return <div>Loading ...</div>;
  if (error?.response.status === 403) navigate("/login")

  return (
    <div className="containerfont-mono">
      <div className="px-20">
      <Navbar />
        <TaskPanel title="🎯 Todo List" tasks={categorizeTasks().todoTasks}>
          <AddTaskButton />
        </TaskPanel>
        <TaskPanel
          title="⏳ In Progess"
          tasks={categorizeTasks().inProgressTasks}
        />
        <TaskPanel title="🏆 Done!" tasks={categorizeTasks().doneTasks} />
      </div>
      <Footer />
    </div>
  );
}

export default Root;
