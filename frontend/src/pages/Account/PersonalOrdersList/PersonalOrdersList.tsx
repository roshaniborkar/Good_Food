import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ShoppingOutlined } from "@ant-design/icons";

import { selectIsOrdersLoading, selectOrders } from "../../../redux-toolkit/orders/orders-selector";
import {
    fetchAllUsersOrders,
    fetchUserOrders,
    fetchUserOrdersByEmail
} from "../../../redux-toolkit/orders/orders-thunks";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import Spinner from "../../../components/Spinner/Spinner";
import { resetOrders } from "../../../redux-toolkit/orders/orders-slice";
import OrdersTable from "../../../components/OrdersTable/OrdersTable";
import { selectUserFromUserState } from "../../../redux-toolkit/user/user-selector";
import { UserRoles } from "../../../types/types";

const PersonalOrdersList: FC = (): ReactElement => {
    const orders = useSelector(selectOrders);
    const isOrdersLoading = useSelector(selectIsOrdersLoading);

    const dispatch = useDispatch();
    const adminOrders = useSelector(selectOrders);
    const isOrderLoading = useSelector(selectIsOrdersLoading);
    const usersData = useSelector(selectUserFromUserState);
    const [isAdmin, setIsAdmin] = useState<boolean>(false);

    useEffect(() => {
        if (usersData) {
            setIsAdmin(usersData.roles![0] === UserRoles.ADMIN);
        }
        dispatch(isAdmin ? fetchAllUsersOrders(0) : fetchUserOrdersByEmail(0));

        return () => {
            dispatch(resetOrders());
        };
    }, []);

    return (
        <>
            {isOrdersLoading ? (
                <Spinner />
            ) : (
                <>
                    {orders.length === 0 ? (
                        <div style={{ textAlign: "center" }}>
                            <ContentTitle title={"You have no orders"} titleLevel={4} icon={<ShoppingOutlined />} />
                        </div>
                    ) : (
                        <>
                            <ContentTitle title={"List of all orders"} titleLevel={4} icon={<ShoppingOutlined />} />
                            <OrdersTable
                                loading={isOrdersLoading}
                                orders={orders}
                                fetchOrders={isAdmin ? fetchUserOrders : fetchUserOrdersByEmail}
                            />
                        </>
                    )}
                </>
            )}
        </>
    );
};

export default PersonalOrdersList;
