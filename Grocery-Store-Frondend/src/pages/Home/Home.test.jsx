import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter, Routes, Route } from "react-router-dom";
import { describe, it, expect } from "vitest";

import Home from "./Home";

describe("Home Page", () => {

    it("should display the home page", () => {
        render(
            <MemoryRouter>
                <Home />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("heading", {
                name: /welcome to grocery store/i
            })
        ).toBeInTheDocument();

        expect(
            screen.getByText(
                /fresh groceries delivered to you doorstep/i
            )
        ).toBeInTheDocument();
    });


    it("should display the Shop Now button", () => {
        render(
            <MemoryRouter>
                <Home />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("button", {
                name: /shop now/i
            })
        ).toBeInTheDocument();
    });


    it("should navigate to products when Shop Now is clicked", async () => {
        const user = userEvent.setup();

        render(
            <MemoryRouter initialEntries={["/"]}>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route
                        path="/products"
                        element={<h1>Products Page</h1>}
                    />
                </Routes>
            </MemoryRouter>
        );

        await user.click(
            screen.getByRole("button", {
                name: /shop now/i
            })
        );

        expect(
            screen.getByRole("heading", {
                name: /products page/i
            })
        ).toBeInTheDocument();
    });


    it("should display top brand cards", () => {
        render(
            <MemoryRouter>
                <Home />
            </MemoryRouter>
        );

        expect(
            screen.getByAltText(/packaged foods/i)
        ).toBeInTheDocument();

        expect(
            screen.getByAltText(/dairy/i)
        ).toBeInTheDocument();

        expect(
            screen.getByAltText(/household/i)
        ).toBeInTheDocument();
    });


    it("should display grocery sections", () => {
        render(
            <MemoryRouter>
                <Home />
            </MemoryRouter>
        );

        expect(screen.getByAltText(/packaged foods/i)).toBeInTheDocument();
        expect(screen.getByAltText(/dairy/i)).toBeInTheDocument();
        expect(screen.getByAltText(/household items/i)).toBeInTheDocument();
    });


    it("should display footer", () => {
        render(
            <MemoryRouter>
                <Home />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("contentinfo")
        ).toBeInTheDocument();
    });

});