import { Navigate, Outlet} from "react-router-dom"

function ProtectedRoutes({redirectTo = "/login", children}) {
    if(!localStorage.getItem("token")) return <Navigate to={redirectTo} />
    return children ? children : <Outlet />
}

export default ProtectedRoutes