import TaskCard from "../Card/TaskCard"

function TaskList({tasks}) {

    return(
        <div className='h-5/6 mx-7 rounded-3xl border-2 border-black-100 
        shadow-xl flex space-x-4 py-5 px-8 overflow-x-auto'>
            {
                tasks.map((item) => (
                    <TaskCard item={item} />    
            ))}
        </div>
    )
}

export default TaskList