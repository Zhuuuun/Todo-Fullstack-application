import { useState } from "react";
import NewTaskModal from "../Modal/NewTaskModal";
import DefaultButton from "../../common/DefaultButton";

function AddTaskButton() {
  const [isOpen, setOpen] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  return (
    <>
      <NewTaskModal isOpen={isOpen} onClose={handleClose} />
      <DefaultButton onClick={handleOpen}>New Task</DefaultButton>
    </>
  );
}

export default AddTaskButton;
