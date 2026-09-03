import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter } from "react-router-dom";
import { describe, it, expect, vi, beforeEach } from "vitest";
import Register from "./Register";
import api from "../../api/api";

vi.mock("../../api/api");

describe("Register Page", () => {

    beforeEach(() => {
        vi.clearAllMocks();
    });

    it("should display register page", () => {

        render(
            <MemoryRouter>
                <Register />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("heading", { name: /create account/i })
        ).toBeInTheDocument();

        expect(
            screen.getByPlaceholderText(/enter your full name/i)
        ).toBeInTheDocument();

        expect(
            screen.getByPlaceholderText(/enter your email/i)
        ).toBeInTheDocument();

        expect(
            screen.getByPlaceholderText(/enter your phone number/i)
        ).toBeInTheDocument();

        expect(
            screen.getByPlaceholderText(/enter your password/i)
        ).toBeInTheDocument();

        expect(
            screen.getByPlaceholderText(/confirm your password/i)
        ).toBeInTheDocument();

        expect(
            screen.getByRole("button", { name: /create account/i })
        ).toBeInTheDocument();
    });


    it("should allow user to enter registration details", async () => {

        const user = userEvent.setup();

        render(
            <MemoryRouter>
                <Register />
            </MemoryRouter>
        );

        const nameInput =
            screen.getByPlaceholderText(/enter your full name/i);

        const emailInput =
            screen.getByPlaceholderText(/enter your email/i);

        const phoneInput =
            screen.getByPlaceholderText(/enter your phone number/i);

        const passwordInput =
            screen.getByPlaceholderText(/enter your password/i);

        await user.type(nameInput, "Muthu");
        await user.type(emailInput, "muthu@gmail.com");
        await user.type(phoneInput, "9876543210");
        await user.type(passwordInput, "password123");

        expect(nameInput).toHaveValue("Muthu");
        expect(emailInput).toHaveValue("muthu@gmail.com");
        expect(phoneInput).toHaveValue("9876543210");
        expect(passwordInput).toHaveValue("password123");
    });


    it("should register user successfully", async () => {

        const user = userEvent.setup();

        api.post.mockResolvedValue({
            data: {
                message: "Registration successful"
            }
        });

        render(
            <MemoryRouter>
                <Register />
            </MemoryRouter>
        );

        await user.type(
            screen.getByPlaceholderText(/enter your full name/i),
            "Muthu"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your email/i),
            "muthu@gmail.com"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your phone number/i),
            "9876543210"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your password/i),
            "password123"
        );

        await user.type(
            screen.getByPlaceholderText(/confirm your password/i),
            "password123"
        );

        await user.click(
            screen.getByRole("button", { name: /create account/i })
        );

        expect(api.post).toHaveBeenCalledWith(
            "/auth/register",
            {
                name: "Muthu",
                email: "muthu@gmail.com",
                number: "9876543210",
                password: "password123"
            }
        );
    });


    it("should handle registration failure", async () => {

        const user = userEvent.setup();

        api.post.mockRejectedValue({
            response: {
                status: 409,
                data: "Email already exists"
            }
        });

        render(
            <MemoryRouter>
                <Register />
            </MemoryRouter>
        );

        await user.type(
            screen.getByPlaceholderText(/enter your full name/i),
            "Muthu"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your email/i),
            "existing@gmail.com"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your phone number/i),
            "9876543210"
        );

        await user.type(
            screen.getByPlaceholderText(/enter your password/i),
            "password123"
        );

        await user.type(
            screen.getByPlaceholderText(/confirm your password/i),
            "password123"
        );

        await user.click(
            screen.getByRole("button", { name: /create account/i })
        );

        expect(api.post).toHaveBeenCalledWith(
            "/auth/register",
            expect.any(Object)
        );
    });


    it("should have login link", () => {

        render(
            <MemoryRouter>
                <Register />
            </MemoryRouter>
        );

        const loginLink = screen.getByRole(
            "link",
            { name: /login/i }
        );

        expect(loginLink).toBeInTheDocument();
        expect(loginLink).toHaveAttribute("href", "/login");
    });

});