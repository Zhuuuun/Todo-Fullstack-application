import { useMutation, useQueryClient } from "react-query";
import { moveNext, removeTask } from "../../../services/TaskService";
import RoundedButton from "../../common/RoundedButton";
import { priorityColorMap } from "../../../utils/TaskUtil";

function TaskCard({ item }) {
  const queryClient = useQueryClient();

  const moveNextMutation = useMutation({
    mutationFn: moveNext,
    onSuccess: () => {
      queryClient.invalidateQueries(["tasks"], { exact: true });
    },
  });

  const handleMoveNext = (e) => {
    e.preventDefault();
    moveNextMutation.mutate({
      taskId: item.taskId,
    });
  };

  const removeTaskMutation = useMutation({
    mutationFn: removeTask,
    onSuccess: () => {
      queryClient.invalidateQueries(["tasks"], { exact: true });
    },
  });

  const handleRemoveTask = (e) => {
    e.preventDefault();
    removeTaskMutation.mutate({
      taskId: item.taskId,
    });
  };

  return (
    <div
      className={`h-full w-1/6 rounded-xl p-3 border-2 border-black-100 shadow-xl flex flex-col shrink-0 ${priorityColorMap(
        item.priority
      )}`}
    >
      <div className="flex justify-between">
        <p className="text-right text-sm">
          {item.dueDate.toString()}
        </p>
        <RoundedButton onClick={handleRemoveTask} />
      </div>
      <div className="h-3/4 flex justify-center items-center">
        <h1 className="font-primary text-lg font-semibold text-center">
          {item.title}
        </h1>
      </div>
      <div className="flex h-1/4 justify-center items-center">
        <button
          onClick={handleMoveNext}
          className="px-4 py-2 rounded-xl bg-zinc-600 text-white"
        >
          Move Next !
        </button>
      </div>
    </div>
  );
}

export default TaskCard;
