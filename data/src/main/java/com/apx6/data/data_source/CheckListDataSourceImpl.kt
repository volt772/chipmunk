package com.apx6.data.data_source

import javax.inject.Inject


class CheckListDataSourceImpl @Inject constructor(): CheckListDataSource
//class TaskDataSourceImpl @Inject constructor(
////    private val mpDatabase: MpDatabase,
//) : CardRemoteDataSource {
//
//    override fun cards(
//        ids: MpdIds,
//        filters: CardFilterParam
//    ): Flow<PagingData<CardsEntity>> {
//
//        /* RemoteMediator 미사용, 로컬 DB만 사용시 Factory*/
//        return { mpDatabase.cardsDao().getCards(
//            sectionId = ids.sectionRowId,
//            title = filters.title,
//            description = filters.description,
//            isCompleted = filters.isCompleted,
//            createdDateTime = filters.createdDateTime
//        )}.run {
//            Pager(
//                config = PagingConfig(
//                    pageSize = LOCAL_PAGE_SIZE,
//                    enablePlaceholders = false,
//                    initialLoadSize = LOCAL_PAGE_SIZE * 2
//                ),
//                pagingSourceFactory = this
//            ).flow
//        }
//
//    }
//}