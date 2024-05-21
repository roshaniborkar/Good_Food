import { createAsyncThunk } from "@reduxjs/toolkit";
import { Button, Col, Form, notification, Row, Upload } from "antd";
import { HeaderResponse, OrderResponse, UserOrdersRequest } from "../../types/types";
import RequestService from "../../utils/request-service";
import {
    ADMIN_GRAPHQL_ORDER,
    ADMIN_GRAPHQL_ORDERS,
    ADMIN_ORDER,
    ADMIN_ORDERS,
    ORDER,
    ORDER_GRAPHQL,
    USER_ORDERS
} from "../../constants/urlConstants";
import { ordersByEmailQuery, ordersByQuery } from "../../utils/graphql-query/orders-query";

export const fetchUserOrders = createAsyncThunk<HeaderResponse<OrderResponse>, number>(
    "orders/fetchUserOrders",
    async (page) => {
        const response = await RequestService.get(`${ORDER}?page=${page}`, true);
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

export const fetchAllUsersOrders = createAsyncThunk<HeaderResponse<OrderResponse>, number>(
    "orders/fetchAllUsersOrders",
    async (page) => {
        const response = await RequestService.get(`${ADMIN_ORDERS}?page=${page}`, true);
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

export const fetchUserOrdersByEmail = createAsyncThunk<HeaderResponse<OrderResponse>, number>(
    "orders/fetchUserOrdersByEmail",
    
    async () => {
        const email = localStorage.getItem("email");
        if(email === null){
            window.scrollTo(0, 0);
            notification.error({
                message: "Please Sign-In to continue",
                description: "Completing this order requires Sign-In"
            });
        }
        const response = await RequestService.get(`${USER_ORDERS}/${email}`, false);
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

// graphql
export const fetchUserOrdersByQuery = createAsyncThunk<Array<OrderResponse>, string>(
    "orders/fetchUserOrdersByQuery",
    async (email) => {
        const response = await RequestService.post(ORDER_GRAPHQL, { query: ordersByEmailQuery(email) }, true);
        return response.data.data.ordersByEmail;
    }
);

export const fetchAllUsersOrdersByQuery = createAsyncThunk<Array<OrderResponse>>(
    "orders/fetchAllUsersOrdersByQuery",
    async () => {
        const response = await RequestService.post(ADMIN_GRAPHQL_ORDERS, { query: ordersByQuery }, true);
        return response.data.data.orders;
    }
);

export const fetchUserOrdersByEmailQuery = createAsyncThunk<Array<OrderResponse>, string>(
    "orders/fetchUserOrdersByEmailQuery",
    async (email) => {
        const response = await RequestService.post(ADMIN_GRAPHQL_ORDER, { query: ordersByEmailQuery(email) }, true);
        return response.data.data.ordersByEmail;
    }
);
