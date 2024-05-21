import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Col, Form, notification, Row, Upload } from "antd";
import { PlusSquareFilled, PlusSquareOutlined, UploadOutlined } from "@ant-design/icons";
import { UploadChangeParam } from "antd/lib/upload/interface";

import {
    selectAdminStateErrors,
    selectIsAdminStateLoading,
    selectIsPerfumeAdded
} from "../../../redux-toolkit/admin/admin-selector";
import { resetAdminState, setAdminLoadingState } from "../../../redux-toolkit/admin/admin-slice";
import { LoadingStatus } from "../../../types/types";
import { addPerfume } from "../../../redux-toolkit/admin/admin-thunks";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import AddFormInput from "./AddFormInput";
import AddFormSelect from "./AddFormSelect";
import IconButton from "../../../components/IconButton/IconButton";

type AddProductData = {
    productName: string;
    productCategory: string;
    productTag: string;
    price: string;
    seller: string;
    review: string;
};

const AddProduct: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const isPerfumeAdded = useSelector(selectIsPerfumeAdded);
    const ispPerfumeLoading = useSelector(selectIsAdminStateLoading);
    const perfumeErrors = useSelector(selectAdminStateErrors);
    const [file, setFile] = React.useState<string>("");

    useEffect(() => {
        dispatch(setAdminLoadingState(LoadingStatus.LOADED));

        return () => {
            dispatch(resetAdminState(LoadingStatus.LOADING));
        };
    }, []);

    useEffect(() => {
        if (isPerfumeAdded) {
            window.scrollTo(0, 0);
            notification.success({
                message: "Perfume added",
                description: "Perfume successfully added!"
            });
            dispatch(resetAdminState(LoadingStatus.SUCCESS));
        }
    }, [isPerfumeAdded]);

    const onFormSubmit = (data: AddProductData): void => {
        const bodyFormData: FormData = new FormData();
        // @ts-ignore
        console.log(data);

        bodyFormData.append("product", new Blob([JSON.stringify({ ...data })], { type: "application/json" }));

        dispatch(addPerfume(bodyFormData));
    };

    const handleUpload = ({ file }: UploadChangeParam<any>): void => {
        setFile(file);
    };

    return (
        <>
            <ContentTitle title={"Add product"} titleLevel={4} icon={<PlusSquareOutlined />} />
            <Form onFinish={onFormSubmit}>
                <Row gutter={32}>
                    <Col span={12}>
                        <AddFormInput
                            title={"Product Name"}
                            name={"productName"}
                            error={perfumeErrors.perfumeTitleError}
                            placeholder={"Enter the product name"}
                            disabled={ispPerfumeLoading}
                        />

                        <AddFormSelect
                            title={"Product Category"}
                            name={"productCategory"}
                            error={perfumeErrors.typeError}
                            placeholder={"Select Category"}
                            disabled={ispPerfumeLoading}
                            values={[
                                "Fresh Fruits",
                                "Fresh Vegetables",
                                "Meat and Seafood",
                                "Cleaning Essentials",
                                "Home and Kitchen",
                                "Beauty and Grooming"
                            ]}
                        />

                        <AddFormInput
                            title={"Product Image"}
                            name={"imagePath"}
                            error={perfumeErrors.fragranceBaseNotesError}
                            placeholder={"Enter the image"}
                            disabled={ispPerfumeLoading}
                        />
                        <AddFormInput
                            title={"Seller"}
                            name={"seller"}
                            error={perfumeErrors.fragranceTopNotesError}
                            placeholder={"Enter the seller"}
                            disabled={ispPerfumeLoading}
                        />
                    </Col>
                    <Col span={12}>
                        <AddFormInput
                            title={"Product Tag"}
                            name={"productTag"}
                            error={perfumeErrors.fragranceMiddleNotesError}
                            placeholder={"Enter the product tag"}
                            disabled={ispPerfumeLoading}
                        />
                        <AddFormInput
                            title={"Price"}
                            name={"price"}
                            error={perfumeErrors.priceError}
                            placeholder={"Enter the price"}
                            disabled={ispPerfumeLoading}
                        />
                        <AddFormInput
                            title={"Rating Stars (on 5)"}
                            name={"review"}
                            error={perfumeErrors.perfumeGenderError}
                            placeholder={"Enter the ratings"}
                            disabled={ispPerfumeLoading}
                        />
                    </Col>
                </Row>
                <IconButton title={"Add"} icon={<PlusSquareFilled />} />
            </Form>
        </>
    );
};

export default AddProduct;
