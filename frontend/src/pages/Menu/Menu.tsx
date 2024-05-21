import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Col, Layout, Pagination, RadioChangeEvent, Row, Typography, Upload } from "antd";
import { CheckboxValueType } from "antd/lib/checkbox/Group";
import { useLocation } from "react-router-dom";

import MenuCheckboxSection from "./MenuSection/MenuCheckboxSection";
import { selectIsPerfumesLoading, selectPerfumes } from "../../redux-toolkit/perfumes/perfumes-selector";
import { FilterParamsType } from "../../types/types";

import {
    fetchPerfumesByFilterParams,
    fetchProductsByInputText,
    uploadFile
} from "../../redux-toolkit/perfumes/perfumes-thunks";
import { resetPerfumesState } from "../../redux-toolkit/perfumes/perfumes-slice";
import MenuSorter from "./MenuSorter/MenuSorter";
import ProductCard from "../../components/ProductCard/ProductCard";
import InputSearch from "../../components/InputSearch/InputSearch";
import Spinner from "../../components/Spinner/Spinner";
import { MAX_PAGE_VALUE, usePagination } from "../../hooks/usePagination";
import { gender, perfumer, price } from "./MenuData";
import { useSearch } from "../../hooks/useSearch";
import "./Menu.css";
import { UploadOutlined } from "@ant-design/icons";
import { UploadChangeParam } from "antd/lib/upload";

export enum CheckboxCategoryFilter {
    PRODUCTS = "PERFUMERS"
}

const Menu: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const perfumes = useSelector(selectPerfumes);
    const isPerfumesLoading = useSelector(selectIsPerfumesLoading);

    const location = useLocation<{ id: string }>();
    const [filterParams, setFilterParams] = useState<FilterParamsType>({
        perfumers: []
    });
    const [selectedFile, setSelectedFile] = useState<File | null>(null); //
    //const uploadStatus = useSelector((state) => state.uploadStatus);
    const [sortByPrice, setSortByPrice] = useState<boolean>(false);
    const { currentPage, totalElements, handleChangePagination, resetPagination } = usePagination();
    const { searchValue, searchTypeValue, resetFields, form, onSearch, handleChangeSelect } = useSearch();
    const file = "";

    const handleUpload1 = ({ file }: UploadChangeParam<any>): void => {
        if (file.status == "removed") {
            dispatch(fetchPerfumesByFilterParams([]));
        }
        setSelectedFile(file); // Update the state with the file from the function argument
        if (file) {
            dispatch(uploadFile(file)); // Dispatch the uploadFile thunk with the selected file
            //resetFields();
            resetPagination();
        }
    };
    const setFile = "";
    useEffect(() => {
        const perfumeData = location.state.id;
        console.log(perfumeData);

        if (perfumeData == "") {
            dispatch(fetchPerfumesByFilterParams([]));
        } else {
            dispatch(fetchPerfumesByFilterParams([perfumeData]));
            filterParams.perfumers.push(perfumeData);
        }
        setFilterParams((prevState) => ({ ...prevState, perfumers: [...prevState.perfumers, perfumeData] }));

        window.scrollTo(0, 0);

        return () => {
            dispatch(resetPerfumesState());
        };
    }, []);

    useEffect(() => {
        resetPagination();
    }, [filterParams, sortByPrice]);

    const onChangeCheckbox = (checkedValues: CheckboxValueType[], category: CheckboxCategoryFilter): void => {
        if (CheckboxCategoryFilter.PRODUCTS === category) {
            setFilterParams((prevState) => {
                const filter = { ...prevState, perfumers: [...(checkedValues as string[])] };
                dispatch(fetchPerfumesByFilterParams([...(checkedValues as string[])]));
                return filter;
            });
        }
        resetFields();
    };

    const onChangeRadio = (event: RadioChangeEvent): void => {
        setFilterParams((prevState) => {
            const filter = { ...prevState, prices: event.target.value };
            dispatch(fetchPerfumesByFilterParams({ ...filter.perfumers }));
            return filter;
        });
        resetFields();
    };

    const handleChangeSortPrice = (event: RadioChangeEvent): void => {
        dispatch(fetchPerfumesByFilterParams({ ...filterParams.perfumers }));
        setSortByPrice(event.target.value);
        resetFields();
    };

    const changePagination = (page: number, pageSize: number): void => {
        if (searchValue) {
            dispatch(fetchProductsByInputText({ searchtext: searchValue }));
        } else {
            dispatch(fetchPerfumesByFilterParams({ ...filterParams.perfumers }));
        }
        handleChangePagination(page, pageSize);
    };

    return (
        <Layout>
            <Layout.Content className={"login-content"}>
                <Typography.Title level={2}>Products</Typography.Title>
                <Row gutter={32}>
                    <Col span={6}>
                        <MenuCheckboxSection
                            title={"Categories"}
                            onChange={onChangeCheckbox}
                            data={perfumer}
                            category={CheckboxCategoryFilter.PRODUCTS}
                            selectedValues={filterParams.perfumers}
                        />
                    </Col>
                    <Col span={18}>
                        <Row>
                            <Col span={100}>
                                <InputSearch onSearch={onSearch} form={form} />

                                <Upload name={"file"} onChange={handleUpload1} beforeUpload={() => false}>
                                    <Button icon={<UploadOutlined />}>Click to Upload</Button>
                                </Upload>
                            </Col>
                        </Row>
                        <Row style={{ marginTop: 16, marginBottom: 16 }}>
                            <Col span={16}>
                                <Pagination
                                    current={currentPage}
                                    pageSize={MAX_PAGE_VALUE}
                                    total={totalElements}
                                    showSizeChanger={false}
                                    onChange={changePagination}
                                />
                            </Col>
                            <Col span={8}>
                                <MenuSorter onChange={handleChangeSortPrice} sortByPrice={sortByPrice} />
                            </Col>
                        </Row>
                        <Row gutter={[32, 32]}>
                            {isPerfumesLoading ? (
                                <Spinner />
                            ) : (
                                perfumes.map((perfume) => (
                                    <ProductCard key={perfume.productId} product={perfume} colSpan={8} />
                                ))
                            )}
                        </Row>
                        <Row style={{ marginTop: 16, marginBottom: 16 }}>
                            <Pagination
                                current={currentPage}
                                pageSize={MAX_PAGE_VALUE}
                                total={totalElements}
                                showSizeChanger={false}
                                onChange={changePagination}
                            />
                        </Row>
                    </Col>
                </Row>
            </Layout.Content>
        </Layout>
    );
};

export default Menu;
