import { createSlice, PayloadAction } from "@reduxjs/toolkit";

import { LoadingStatus, ProductResponse } from "../../types/types";
import {
    fetchProducts,
    fetchPerfumesByFilterParams,
    fetchProductsByIds,
    fetchPerfumesByIdsQuery,
    uploadFile,
    fetchProductsByInputText,
    fetchPerfumesByQuery
} from "./perfumes-thunks";

export interface PerfumesState {
    perfumes: Array<ProductResponse>;
    pagesCount: number;
    totalElements: number;
    loadingState: LoadingStatus;
}

export const initialState: PerfumesState = {
    perfumes: [],
    pagesCount: 1,
    totalElements: 0,
    loadingState: LoadingStatus.LOADING
};

export const perfumesSlice = createSlice({
    name: "perfumes",
    initialState,
    reducers: {
        setPerfumes(state, action: PayloadAction<Array<ProductResponse>>) {
            state.perfumes = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        },
        removePerfumeById(state, action: PayloadAction<number>) {
            state.perfumes = state.perfumes.filter((perfume) => perfume.productId !== action.payload);
            state.loadingState = LoadingStatus.LOADED;
        },
        resetPerfumesState: () => initialState
    },
    extraReducers: (builder) => {
        builder.addCase(fetchProducts.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProducts.fulfilled, (state, action) => {
            state.perfumes = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByIds.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByIds.fulfilled, (state, action) => {
            state.perfumes = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchPerfumesByFilterParams.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchPerfumesByFilterParams.fulfilled, (state, action) => {
            state.perfumes = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByInputText.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByInputText.fulfilled, (state, action) => {
            state.perfumes = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(uploadFile.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(uploadFile.fulfilled, (state, action) => {
            state.perfumes = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchPerfumesByQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchPerfumesByQuery.fulfilled, (state, action) => {
            state.perfumes = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchPerfumesByIdsQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchPerfumesByIdsQuery.fulfilled, (state, action) => {
            state.perfumes = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
    }
});

export const { setPerfumes, removePerfumeById, resetPerfumesState } = perfumesSlice.actions;
export default perfumesSlice.reducer;
