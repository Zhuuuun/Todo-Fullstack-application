import DropdownInput from "../../common/DropdownInput";
import DefaultButton from "../../common/DefaultButton";
import Input from "../../common/Input";
import { useMutation, useQueryClient } from "react-query";
import { useRef, useState } from "react";
import { createTask } from "../../../services/TaskService";
import { getUserName } from "../../../services/UserService";
import { priorityEncode } from "../../../utils/TaskUtil";

const options = ["Emergency", "Urgent", "Normal", "Snail"];

function ModalInput({ onClose }) {
  const queryClient = useQueryClient();

  const title = useRef();
  const dueDate = useRef();
  const priority = useRef();

  const [error, setError] = useState(null);

  const createTaskMutation = useMutation({
    mutationFn: createTask,
    onSuccess: () => {
      setError(null)
      onClose()
      queryClient.invalidateQueries(["tasks"], { exact: true });
    },
    onError: (err) => {
      setError(err.response.data)
    },
  });

  const handleClick = (e) => {
    e.preventDefault();

    const userName = getUserName();
    createTaskMutation.mutate({
      body: {
        userName: userName,
        title: title.current.value,
        dueDate: dueDate.current.value,
        priority: priorityEncode(priority.current.value),
      },
    });
    
  };

  return (
    <div className="flex flex-col space-y-10 h-full">
      <div>
        <Input type="text" fieldName="Title" ref={title} error={error?.title} />
      </div>

      <div className="flex space-x-20 w-full">
        <div className="w-1/2">
          <Input type="date" fieldName="Due date" ref={dueDate} error={error?.dueDate || error?.errorMessage} />
        </div>
        <div className="w-1/2">
          <DropdownInput
            fieldName="Priority"
            options={options}
            ref={priority}
            error={error?.priority}
          />
        </div>
      </div>

      <div className="h-full flex justify-end items-end">
        <div className="flex justify-end space-x-5">
          <DefaultButton onClick={handleClick}>Saved</DefaultButton>
          <DefaultButton onClick={() => {setError(null); onClose();}}>Cancel</DefaultButton>
        </div>
      </div>
    </div>
  );
}

export default ModalInput;
