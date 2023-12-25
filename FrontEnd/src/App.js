import logo from "./logo.svg";
import "./App.css";
import { Layout, Typography } from "antd";
import LoginForm from "./components/LoginForm";
import SignupForm from "./components/SignupForm";
import FoodList from "./components/FoodList";
import MyCart from "./components/MyCart";
import { useState } from "react";

const { Header, Content } = Layout;
const { Title } = Typography;

const App = () => {
  const [authed, setAuthed] = useState(false);

  return (
    <Layout style={{ height: "100vh" }}>
      <Header>
        <div
          className="header"
          style={{ display: "flex", justifyContent: "space-between" }}
        >
          <Title
            level={2}
            style={{ color: "white", lineHeight: "inherit", marginBottom: 0 }}
          >
            Lai Food
          </Title>
          <div>{authed ? <MyCart /> : <SignupForm />}</div>
        </div>
      </Header>
      <Content
        style={{
          padding: "50px",
          maxHeight: "calc(100% - 64px)",
          overflowY: "auto",
        }}
      >
        {authed ? (
          <FoodList></FoodList>
        ) : (
          <LoginForm
            onSuccess={() => {
              setAuthed(true);
            }}
          ></LoginForm>
        )}
      </Content>
    </Layout>
  );
};

export default App;
