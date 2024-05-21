import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { EditOutlined, UploadOutlined } from "@ant-design/icons";
import { Button, Col, Form, notification, Row, Upload } from "antd";
import { UploadChangeParam } from "antd/lib/upload/interface";

import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import FormInput from "../../../components/FormInput/FormInput";
import { selectPerfume } from "../../../redux-toolkit/product/product-selector";
import {
    selectAdminStateErrors,
    selectIsAdminStateLoading,
    selectIsPerfumeEdited
} from "../../../redux-toolkit/admin/admin-selector";
import { LoadingStatus } from "../../../types/types";
import { resetAdminState, setAdminLoadingState } from "../../../redux-toolkit/admin/admin-slice";
import { fetchPerfume } from "../../../redux-toolkit/product/product-thunks";
import IconButton from "../../../components/IconButton/IconButton";
import EditPerfumeSelect from "./EditProductSelect";
import { updatePerfume } from "../../../redux-toolkit/admin/admin-thunks";
import "./EditProduct.css";

type EditPerfumeData = {
    perfumeTitle: string;
    perfumer: string;
    year: string;
    country: string;
    type: string;
    volume: string;
    perfumeGender: string;
    fragranceTopNotes: string;
    fragranceMiddleNotes: string;
    fragranceBaseNotes: string;
    price: string;
};

const EditPerfume: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const params = useParams<{ id: string }>();
    const perfumeData = useSelector(selectPerfume);
    const isLoading = useSelector(selectIsAdminStateLoading);
    const errors = useSelector(selectAdminStateErrors);
    const isPerfumeEdited = useSelector(selectIsPerfumeEdited);
    const [file, setFile] = React.useState<string>("");

    useEffect(() => {
        dispatch(setAdminLoadingState(LoadingStatus.LOADED));
        dispatch(fetchPerfume(params.id));

        return () => {
            dispatch(resetAdminState(LoadingStatus.LOADING));
        };
    }, []);

    useEffect(() => {
        if (perfumeData) {
            form.setFieldsValue(perfumeData);
        }
    }, [perfumeData]);

    useEffect(() => {
        if (isPerfumeEdited) {
            window.scrollTo(0, 0);
            notification.success({
                message: "Product edited",
                description: "Product successfully edited!"
            });
            dispatch(resetAdminState(LoadingStatus.SUCCESS));
        }
    }, [isPerfumeEdited]);

    const onFormSubmit = (data: EditPerfumeData): void => {
        const bodyFormData: FormData = new FormData();
        // @ts-ignore

        bodyFormData.append(
            "product",
            new Blob([JSON.stringify({ ...data, productId: perfumeData?.productId })], { type: "application/json" })
        );

        dispatch(updatePerfume(bodyFormData));
    };

    const handleUpload = ({ file }: UploadChangeParam<any>): void => {
        setFile(file);
    };

    return (
        <div>
            <ContentTitle title={"Edit product"} titleLevel={4} icon={<EditOutlined />} />
            <Form onFinish={onFormSubmit} form={form}>
                <Row gutter={32}>
                    <Col span={12}>
                        <FormInput
                            titleSpan={6}
                            wrapperSpan={18}
                            error={errors.perfumeTitleError}
                            disabled={isLoading}
                            title={"Product Name"}
                            name={"productName"}
                            placeholder={"Enter the product name"}
                        />

                        <EditPerfumeSelect
                            error={errors.typeError}
                            disabled={isLoading}
                            title={"Product Category"}
                            name={"productCategory"}
                            placeholder={"Select Category"}
                            values={[
                                "Fresh Fruits",
                                "Fresh Vegetables",
                                "Meat and Seafood",
                                "Cleaning Essentials",
                                "Home and Kitchen",
                                "Beauty and Grooming"
                            ]}
                        />

                        <FormInput
                            titleSpan={6}
                            wrapperSpan={18}
                            error={errors.volumeError}
                            disabled={isLoading}
                            title={"Product Image"}
                            name={"imagePath"}
                            placeholder={"Enter the image"}
                        />
                        <FormInput
                            titleSpan={6}
                            wrapperSpan={18}
                            error={errors.fragranceTopNotesError}
                            disabled={isLoading}
                            title={"Product Tag"}
                            name={"productTag"}
                            placeholder={"Enter the product tag"}
                        />
                        <FormInput
                            titleSpan={6}
                            wrapperSpan={18}
                            error={errors.fragranceMiddleNotesError}
                            disabled={isLoading}
                            title={"Price"}
                            name={"price"}
                            placeholder={"Enter the price"}
                        />
                    </Col>
                </Row>
                <IconButton title={"Edit"} icon={<EditOutlined />} disabled={isLoading} />
            </Form>
        </div>
    );
};

export default EditPerfume;
