package org.techtown.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.techtown.data.model.UserModel
import org.techtown.data.model.UserRootModel

@Parcelize
data class PresentationUserRootModel(
    val incomplete_results: Boolean,
    val items: ArrayList<PresentationUserModel>,
    val total_count: Int
) : Parcelable {
    companion object {


        fun toDataModel(
            presentationUserRootModel: PresentationUserRootModel
        ): UserRootModel {
            return UserRootModel(
                incomplete_results = presentationUserRootModel.incomplete_results,
                items = presentationUserRootModel.items.map { PresentationUserModel.toDataModel(it) } as ArrayList<PresentationUserModel>,
                total_count = presentationUserRootModel.total_count
            )
        }

        //프레젠 테이션 모듈 데이터 모델로.
        fun toPresentationModel(userRootrModel: UserRootModel): PresentationUserRootModel {
            return PresentationUserRootModel(
                incomplete_results = userRootrModel.incomplete_results,
                items = userRootrModel.items.map { PresentationUserModel.toPresentationModel(it) } as ArrayList<PresentationUserModel>,
                total_count = userRootrModel.total_count
            )
        }
    }
}