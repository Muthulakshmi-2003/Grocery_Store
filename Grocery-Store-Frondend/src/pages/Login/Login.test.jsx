import {render , screen} from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter } from "react-router-dom";
import {describe , it , expect , vi , beforeEach} from "vitest";
import Login from "../Login/Login";
import api from "../../api/api";

vi.mock("../../api/api");

describe("Login page",()=>{
    beforeEach(()=>{
        localStorage.clear();
        vi.clearAllMocks();
    });
    it("should display login page",()=>{
        render(
            <MemoryRouter>
                <Login/>
            </MemoryRouter>
        );
       
        expect(screen.getByPlaceholderText(/email/i))
        .toBeInTheDocument();
        expect(screen.getByPlaceholderText(/password/i))
        .toBeInTheDocument();
    });
    it("should login as admin and navigate to dashboard", async () => {
        const user = userEvent.setup();

        render(
            <MemoryRouter>
                <Login />
            </MemoryRouter>
        );

        await user.type(
            screen.getByPlaceholderText(/email/i),
            "admin@gmail.com"
        );

        await user.type(
            screen.getByPlaceholderText(/password/i),
            "admin123"
        );



        expect(window.location.pathname).toBe("/");
    });


    it("should login customer successfully", async () => {
        const user = userEvent.setup();

        api.post.mockResolvedValue({
            data: {
                token: "test-token",
                customerId: 101,
                name: "Muthu",
                email: "muthu@gmail.com"
            }
        });

        render(
            <MemoryRouter>
                <Login />
            </MemoryRouter>
        );

        await user.type(
            screen.getByPlaceholderText(/email/i),
            "muthu@gmail.com"
        );

        await user.type(
            screen.getByPlaceholderText(/password/i),
            "password123"
        );
         await user.click(
        screen.getByRole("button", { name: /login/i })
    );

        expect(api.post).toHaveBeenCalledWith(
            "/auth/login",
            {
                email: "muthu@gmail.com",
                password: "password123"
            }
        );

        expect(localStorage.getItem("token"))
            .toBe("test-token");

        expect(localStorage.getItem("user"))
            .toContain("muthu@gmail.com");

        expect(window.location.pathname).toBe("/");
    });


    it("should handle login failure", async () => {
        const user = userEvent.setup();

        api.post.mockRejectedValue({
            response: {
                status: 401,
                data: "Invalid credentials"
            }
        });

        render(
            <MemoryRouter>
                <Login />
            </MemoryRouter>
        );

        await user.type(
            screen.getByPlaceholderText(/email/i),
            "wrong@gmail.com"
        );

        await user.type(
            screen.getByPlaceholderText(/password/i),
            "wrongpassword"
        );
        await user.click(
        screen.getByRole("button", { name: /login/i })
    );
        expect(api.post).toHaveBeenCalled();
    });


})