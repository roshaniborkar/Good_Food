import { createAsyncThunk } from "@reduxjs/toolkit";

import RequestService from "../../utils/request-service";
import {
    PERFUMES as PRODUCTS,
    PRODUCTS_GRAPHQL_IDS,
    PRODUCTS_GRAPHQL_PERFUMES,
    PRODUCTS_IDS,
    PRODUCTS_SEARCH,
    PRODUCTS_IMAGE_SEARCH,
    PRODUCTS_SEARCH_TEXT as PRODUCTS_SEARCH_TEXT
} from "../../constants/urlConstants";
import { FilterParamsType, HeaderResponse, ProductResponse as ProductsResponse, PerfumesSearchRequest as ProductsSearchRequest } from "../../types/types";
import { gePerfumesByIdsQuery, getAllPerfumesByQuery } from "../../utils/graphql-query/perfume-query";

export const fetchProducts = createAsyncThunk<HeaderResponse<ProductsResponse>, number>(
    "perfumes/fetchPerfumes",
    async (page) => {
        const response = await RequestService.get(`${PRODUCTS}?page=${page}`);
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

export const fetchProductsByIds = createAsyncThunk<Array<ProductsResponse>, Array<number>>(
    "perfumes/fetchPerfumesByIds",
    async (ids) => {
        const response = await RequestService.post(PRODUCTS_IDS, ids);
        return response.data;
    }
);

export const uploadFile = createAsyncThunk<HeaderResponse<ProductsResponse>, File>(
    "file/upload",
    async (file) =>{
        const formData = new FormData();
        formData.append('file', file);
  
        const response = await RequestService.post(PRODUCTS_IMAGE_SEARCH, formData, false, 'multipart/form-data');
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
    
 );

export const fetchPerfumesByFilterParams = createAsyncThunk<HeaderResponse<ProductsResponse>, Array<string>>(
    "perfumes/fetchPerfumesByFilterParams",
    async (filter) => {
        const response = await RequestService.post(`${PRODUCTS_SEARCH}`, filter);
        return {
            
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

export const fetchProductsByInputText = createAsyncThunk<HeaderResponse<ProductsResponse>, ProductsSearchRequest>(
    "perfumes/fetchPerfumesByInputText",
    async (data) => {
        const response = await RequestService.get(`${PRODUCTS_SEARCH_TEXT}/`+data.searchtext);
        return {
            items: response.data,
            pagesCount: parseInt(response.headers["page-total-count"]),
            totalElements: parseInt(response.headers["page-total-elements"])
        };
    }
);

// GraphQL thunks
export const fetchPerfumesByQuery = createAsyncThunk<Array<ProductsResponse>>("perfumes/fetchPerfumesByQuery", async () => {
    const response = await RequestService.post(PRODUCTS_GRAPHQL_PERFUMES, { query: getAllPerfumesByQuery });
    return response.data.data.perfumes;
});

export const fetchPerfumesByIdsQuery = createAsyncThunk<Array<ProductsResponse>, Array<number>>(
    "perfumes/fetchPerfumesByIdsQuery",
    async (ids) => {
        const response = await RequestService.post(PRODUCTS_GRAPHQL_IDS, { query: gePerfumesByIdsQuery(ids) });
        return response.data.data.perfumesIds;
    }
);
