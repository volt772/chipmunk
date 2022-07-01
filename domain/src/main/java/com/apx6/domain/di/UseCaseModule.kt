package com.apx6.domain.di

import com.apx6.domain.repository.UserRepository
import com.apx6.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * UseCaseModule
 */

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideUserUseCase(
        userRepository: UserRepository
    ) = UserUseCase(userRepository)
//
//    @Provides
//    fun provideLabelUseCase(
//        labelRepository: LabelRepository,
//        labelActionRepository: LabelActionRepository,
//    ) = LabelUseCase(labelRepository, labelActionRepository)
//
//    @Provides
//    fun provideCrmUseCase(
//        crmRepository: CrmRepository
//    ) = CrmUseCase(crmRepository)
//
//    @Provides
//    fun provideBoardUseCase(
//        boardRepository: BoardRepository
//    ) = BoardUseCase(boardRepository)
//
//    @Provides
//    fun provideSectionUseCase(
//        sectionRepository: SectionRepository,
//        sectionActionRepository: SectionActionRepository
//    ) = SectionUseCase(sectionRepository, sectionActionRepository)
//
//    @Provides
//    fun provideSpaceUseCase(
//        spaceRepository: SpaceRepository
//    ) = SpaceUseCase(spaceRepository)
//
//    @Provides
//    fun provideCardUseCase(
//        cardRepository: CardRepository,
//        cardActionRepository: CardActionRepository,
//        logRepository: LogRepository
//    ) = CardUseCase(cardRepository, cardActionRepository, logRepository)
//
//    @Provides
//    fun provideAssigneeUseCase(
//        assigneeRepository: AssigneeRepository
//    ) = AssigneeUseCase(assigneeRepository)
//
//    @Provides
//    fun provideCheckListUseCase(
//        checkListRepository: CheckListRepository
//    ) = CheckListUseCase(checkListRepository)
//
//    @Provides
//    fun provideCardLabelUseCase(
//        cardLabelRepository: CardLabelRepository
//    ) = CardLabelUseCase(cardLabelRepository)
//
//    @Provides
//    fun provideLogUseCase(
//        logRepository: LogRepository,
//        logActionRepository: LogActionRepository
//    ) = LogUseCase(logRepository, logActionRepository)
//
//    @Provides
//    fun provideAttachmentUseCase(
//        attachRepository: AttachRepository
//    ) = AttachmentUseCase(attachRepository)
//
//    @Provides
//    fun provideSettingsUseCase(
//        settingsRepository: SettingsRepository
//    ) = SettingsUseCase(settingsRepository)
//
//    @Provides
//    fun providePushConnectionUseCase(
//        pushConnectionRepository: PushConnectionRepository
//    ) = PushConnectionUseCase(pushConnectionRepository)
//
}