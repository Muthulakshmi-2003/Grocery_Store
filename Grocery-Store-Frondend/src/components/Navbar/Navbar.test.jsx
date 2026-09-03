import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter, Routes, Route } from "react-router-dom";
import { describe, it, expect, beforeEach } from "vitest";

import Navbar from "../Navbar/Navbar";

describe("Navbar", () => {

    beforeEach(() => {
        localStorage.clear();
    });


    it("should display navbar elements", () => {

        render(
            <MemoryRouter>
                <Navbar />
            </MemoryRouter>
        );

        expect(
            screen.getByAltText("Grocery Store Logo")
        ).toBeInTheDocument();

        expect(
            screen.getByAltText("searchicon")
        ).toBeInTheDocument();

        expect(
            screen.getByRole("link", { name: /home/i })
        ).toBeInTheDocument();

        expect(
            screen.getByRole("link", { name: /products/i })
        ).toBeInTheDocument();

        expect(
            screen.getByRole("link", { name: /cart/i })
        ).toBeInTheDocument();

        expect(
            screen.getByRole("link", { name: /login/i })
        ).toBeInTheDocument();
    });


    it("should navigate to search page when search button is clicked", async () => {

        const user = userEvent.setup();

        render(
            <MemoryRouter initialEntries={["/"]}>
                <Routes>
                    <Route path="/" element={<Navbar />} />

                    <Route
                        path="/search"
                        element={<h1>Search Page</h1>}
                    />
                </Routes>
            </MemoryRouter>
        );

        const searchButton = screen
            .getByAltText("searchicon")
            .closest("button");

        await user.click(searchButton);

        expect(
            screen.getByRole("heading", {
                name: /search page/i
            })
        ).toBeInTheDocument();
    });


    it("should have Products link", () => {

        render(
            <MemoryRouter>
                <Navbar />
            </MemoryRouter>
        );

        const productsLink = screen.getByRole(
            "link",
            { name: /products/i }
        );

        expect(productsLink).toHaveAttribute(
            "href",
            "/products"
        );
    });


    it("should have Cart link", () => {

        render(
            <MemoryRouter>
                <Navbar />
            </MemoryRouter>
        );

        const cartLink = screen.getByRole(
            "link",
            { name: /cart/i }
        );

        expect(cartLink).toHaveAttribute(
            "href",
            "/cart"
        );
    });


    it("should display Login when user is not logged in", () => {

        localStorage.removeItem("token");

        render(
            <MemoryRouter>
                <Navbar />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("link", { name: /login/i })
        ).toBeInTheDocument();
    });


    it("should display profile button when user is logged in", () => {

        localStorage.setItem("token", "test-token");

        render(
            <MemoryRouter>
                <Navbar />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("button", {
                name: ""
            })
        ).toBeInTheDocument();
    });


    it("should navigate to profile when profile button is clicked", async () => {

        const user = userEvent.setup();

        localStorage.setItem("token", "test-token");

        render(
            <MemoryRouter initialEntries={["/"]}>
                <Routes>
                    <Route path="/" element={<Navbar />} />

                    <Route
                        path="/profile"
                        element={<h1>Profile Page</h1>}
                    />
                </Routes>
            </MemoryRouter>
        );

        const profileButton = document.querySelector(
            ".navbar-user-icon"
        );

        await user.click(profileButton);

        expect(
            screen.getByRole("heading", {
                name: /profile page/i
            })
        ).toBeInTheDocument();
    });

});