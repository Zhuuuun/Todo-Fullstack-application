import { BrowserRouter, Route, Routes } from "react-router-dom";
import { QueryClient, QueryClientProvider } from "react-query";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Root from "./pages/Root";
import ProtectedRoutes from "./routes/ProtectedRoutes";
import PublicRoutes from "./routes/PublicRoutes";

function App() {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <BrowserRouter>
        <Routes>
          <Route
            index
            element={
              <ProtectedRoutes redirectTo="/login">
                <Root />
              </ProtectedRoutes>
            }
          />
          <Route path="/login" element={
              <PublicRoutes redirectTo="/">
                  <Login />
              </PublicRoutes>
            } 
          />

          <Route path="/register" element={
              <PublicRoutes redirectTo="/">
                  <Register />
              </PublicRoutes>      
            } 
          />
        </Routes>
      </BrowserRouter>
    </QueryClientProvider>
  );
}

export default App;
