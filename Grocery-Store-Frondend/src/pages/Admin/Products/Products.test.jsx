import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter, Routes, Route } from "react-router-dom";

import Dashboard from "../Dashboard/Dashboard";


import { describe, it, expect } from "vitest";

describe("Admin Dashboard", () => {

    it("should navigate to Products page", async () => {

        const user = userEvent.setup();

        render(
            <MemoryRouter initialEntries={["/admin/dashboard"]}>

                <Routes>

                    <Route
                        path="/admin/dashboard"
                        element={<Dashboard />}
                    />

                    <Route
                        path="/admin/products"
                        element={<h1>Products Page</h1>}
                    />

                </Routes>

            </MemoryRouter>
        );

        await user.click(
            screen.getByRole("button", {
                name: "Products"
            })
        );

        expect(
            screen.getByRole("heading", {
                name: "Products Page"
            })
        ).toBeInTheDocument();

    });

});