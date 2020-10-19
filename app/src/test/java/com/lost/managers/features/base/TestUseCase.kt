package com.lost.managers.features.base

import javax.inject.Inject

class TestUseCase @Inject constructor() {
    operator fun invoke() = "test"
}