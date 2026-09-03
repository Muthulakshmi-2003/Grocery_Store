import { render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import { describe, it, expect } from "vitest";

import Dashboard from "../Dashboard/Dashboard";

describe("Admin Dashboard", () => {

    it("should display Dashboard heading", () => {

        render(
            <MemoryRouter>
                <Dashboard />
            </MemoryRouter>
        );

        expect(
            screen.getByRole("heading", {
                name: "Dashboard"
            })
        ).toBeInTheDocument();

    });

});