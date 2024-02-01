import TaskList from "../List/TaskList";
import TitleText from "../../common/TitleText";

function TaskPanel({ title, tasks, children }) {
  return (
    <div className="my-10 w-full h-80 flex flex-col justify-end">
      <div className="w-full flex justify-between px-8 my-2">
        <div className="my-3">
          <TitleText>{title}</TitleText>
        </div>
        {children}
      </div>
      <TaskList tasks={tasks} />
    </div>
  );
}

export default TaskPanel;
