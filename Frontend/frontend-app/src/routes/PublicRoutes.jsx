import { Navigate, Outlet} from "react-router-dom"

function PublicRoutes({redirectTo,children}) {
    if(localStorage.getItem("token")) return <Navigate to={redirectTo} />
    return children ? children : <Outlet />
}

export default PublicRoutes