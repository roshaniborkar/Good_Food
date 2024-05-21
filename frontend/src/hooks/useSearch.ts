import { useState } from "react";
import { useDispatch } from "react-redux";
import {Form, FormInstance} from "antd";

import { SearchPerfume as SearchProduct } from "../types/types";
import { fetchProductsByInputText as fetchProductsByInputText } from "../redux-toolkit/perfumes/perfumes-thunks";

interface UseSearch {
    form: FormInstance<any>,
    searchTypeValue: SearchProduct,
    onSearch: (data: {searchValue: string}) => void,
    resetFields: () => void,
    searchValue: string,
    handleChangeSelect: (value: SearchProduct) => void
}

export const useSearch = (): UseSearch => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const [searchTypeValue, setSearchTypeValue] = useState<SearchProduct>(SearchProduct.BRAND);
    const [searchValue, setSearchValue] = useState<string>("");

    const handleChangeSelect = (value: SearchProduct): void => {
        setSearchTypeValue(value);
    };

    const onSearch = (data: { searchValue: string }): void => {
        setSearchValue(data.searchValue);
        dispatch(fetchProductsByInputText({  searchtext: data.searchValue}));
    };

    const resetFields = (): void => {
        form.resetFields();
    };

    return { searchValue, searchTypeValue, form, resetFields, handleChangeSelect, onSearch };
};
