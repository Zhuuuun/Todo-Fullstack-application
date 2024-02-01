export const priorityEncode = (priority) => {
    switch(priority) {
        case "Emergency" : return 4
        case "Urgent" : return 3
        case "Normal" : return 2
        case "Snail" : return 1
        default : return 0
    }
}

export const priorityColorMap = (priority) => {
    switch(priority) {
        case 4 : return "bg-gradient-to-r from-pink-300 to-red-400"
        case 3 : return "bg-gradient-to-r from-amber-200 to-red-300" 
        case 2 : return "bg-gradient-to-r from-yellow-100 to-orange-200"
        case 1 : return "bg-gradient-to-r from-lime-100 to-emerald-200"
        default : return "bg-white"
    }
}